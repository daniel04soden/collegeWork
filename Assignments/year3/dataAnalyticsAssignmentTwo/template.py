#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns
from sklearn import tree
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from scipy.stats import pearsonr
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler


"""
Created on Sat Nov 22 17:00:02 2025

Name: Daniel Soden
Student ID: R00247215
Cohort: SDH3B

"""

"""
Imports
"""

"""
General Functions
"""


def isolate_numeric(df) -> pd.DataFrame:
    """
    Simple approach derived from notes using to numeric and errors coerce
    to convert to all numeric and drop any that aren't
    """
    df = df.apply(pd.to_numeric, errors="coerce").copy()
    df.dropna(how="all", axis=1, inplace=True)

    return df


def isolate_non_numeric(df: pd.DataFrame) -> pd.DataFrame:
    """
    Takes an approach using apply on all columns to convert to a float,
    all ints,longs,floats etc would be possible to be converted but
    non-numeric data can't, using a try except block with valuerror,
    we can return all that do have a valuerror and then recreate a dataframe
    with only those columns. Redefining df is quite ineffecient, but methods used
    for effecient solutions are not found in the notes
    """
    result_data = {}

    def isolation(cell):
        try:
            float(cell)
            return np.nan
        except ValueError:
            return cell

    for cols in df.columns.to_list():
        result_series = df[cols].apply(isolation)
        result_data[cols] = result_series

    res = pd.DataFrame(result_data)
    res = res.dropna(how="all", axis=1)
    return res


def test_isolate_non_numeric():
    file_name_one = "health.csv"
    df = pd.read_csv(file_name_one)
    df = isolate_non_numeric(df)
    valid_test = True
    for cols in df.columns.to_list():
        print(df[cols].sample(1))


def test_isolate_numeric():
    file_name_one = "loan.csv"
    df = pd.read_csv(file_name_one)
    df = isolate_numeric(df)
    print(df.head(5))
    for cols in df.columns.to_list():
        print(df[cols].sample(1))


"""
Task 1
"""


def task_1(file_name: str):
    def task_1_data_cleansing(file_name: str):
        df = pd.read_csv(file_name)
        possible_genders = ["Female", "Male"]
        df["Gender"] = df["Gender"].replace(
            {"Mal": possible_genders[1], "Femal": possible_genders[0]}
        )  # Mal and Femal discovered upon plotting

        male_count = (df["Gender"] == "Male").sum()
        female_count = (df["Gender"] == "Female").sum()

        more_females = female_count > male_count
        more_males = male_count > female_count

        missing_rows = df["Gender"].isnull()

        if more_females:
            df.loc[missing_rows, "Gender"] = "Female"
        elif more_males:
            df.loc[missing_rows, "Gender"] = "Male"
        else:
            pass

        return df

    def task_1_visualisation(file_name: str):
        clean_data = task_1_data_cleansing(file_name)
        gender_counts = clean_data["Gender"].value_counts().reset_index()

        values = gender_counts["count"].values
        labels = gender_counts["Gender"].to_list()

        plt.pie(values, labels=labels, autopct="%1.1f%%", shadow=True, startangle=90)
        plt.title("Gender Distribution")
        plt.show()
        male_count = (clean_data["Gender"] == "Male").sum()
        female_count = (clean_data["Gender"] == "Female").sum()
        print(f"Number of males: {male_count}")
        print(f"Number of females: {female_count}")

    task_1_visualisation(file_name)


"""
Task 2
"""


def task_2(file_name: str):
    df = pd.read_csv(file_name)

    def organise_workout_types(df: pd.DataFrame):
        return df[["Workout_Type", "Calories_Burned", "Session_Duration (hours)"]]

    grouped_workouts = organise_workout_types(df)

    def plot_workout_types(grouped_workouts: pd.DataFrame):
        sns.scatterplot(
            data=grouped_workouts,
            x="Session_Duration (hours)",
            y="Calories_Burned",
            hue="Workout_Type",
        )

        plt.title("Calories Burned vs. Session Duration by Workout Type")
        plt.xlabel("Session Duration (hours)")
        plt.ylabel("Calories Burned")

        plt.show()

    plot_workout_types(grouped_workouts)
    """
    Generally we can see from the scatter plot above, that no matter how little time you give for a session,
    high intensity interval training (HIIT) always comes out on top for calories burned. 
    We can also observe how little of an increase is seen in the increase in calories burned for yoga 
    despite an increase in session duration. 
    """


"""
Task 3
"""


def task_3(file_name: str):
    df = pd.read_csv(file_name)
    df = isolate_numeric(df.copy())
    all_columns = df.columns.to_list()
    for col in all_columns:
        data = df[col]

        q1 = data.quantile(0.33)
        q3 = data.quantile(0.67)

        bins = [data.min(), q1, q3, data.max()]
        labels = [1, 2, 3]

        y = pd.cut(data, bins=bins, labels=labels, duplicates="drop").astype("category")

        y = y.dropna()

        X = df.drop(columns=[col]).loc[y.index]

        clf = tree.DecisionTreeClassifier()
        clf = clf.fit(X, y)

        importance = clf.feature_importances_
        feature_names = X.columns

        importance_series = pd.Series(importance, index=feature_names)
        sorted_importance = importance_series.sort_values(ascending=False)
        sorted_importance = sorted_importance[sorted_importance > 0.25]

        if len(sorted_importance) >= 3:
            print(f"Class Attribute: {col}")
            print("Important Features:")
            for feature, imp in sorted_importance.items():
                if imp > 0.15:
                    print(f" - {feature}: {imp:.4f}")
            print("\n")


"""
Task 4
"""


def task_4_plotting(
    hyperparameters, train_scores, test_scores, hp_label, algorithm_name
):
    plt.figure(figsize=(10, 6))

    plt.plot(
        hyperparameters,
        train_scores,
        label="Accuracy on Training Data",
    )

    plt.plot(
        hyperparameters,
        test_scores,
        label="Accuracy on Test Data",
    )

    plt.title(f"Overfitting Analysis for {algorithm_name}")
    plt.xlabel(hp_label)
    plt.ylabel("Accuracy")
    plt.legend()
    plt.show()


def task_4(file_name: str, which_algorithm: str):
    df = pd.read_csv(file_name)
    df = isolate_numeric(df.copy())
    predict_col = "total_credit_limit"
    df["limit_class"] = pd.cut(
        df[predict_col], bins=3, labels=[0, 1, 2], include_lowest=True
    )

    target_data = df["limit_class"]
    desired_data = df.drop(columns=[predict_col, "limit_class"])

    X_train, X_test, y_train, y_test = train_test_split(
        desired_data, target_data, test_size=0.2, random_state=42
    )

    results = []

    if which_algorithm == "dtc":
        hp_label = "Depth"
        for i in range(1, 20):
            clf = tree.DecisionTreeClassifier(max_depth=i, random_state=42)
            clf.fit(X_train, y_train)
            train_score = clf.score(X_train, y_train)
            test_score = clf.score(X_test, y_test)
            print(
                f"{hp_label}: {i}, Train Score: {train_score:.4f}, Test Score: {test_score:.4f}"
            )
            results.append((i, train_score, test_score))

    elif which_algorithm == "knn":
        hp_label = "No. Neighbors"
        for k in range(1, 20):
            clf = KNeighborsClassifier(n_neighbors=k)
            clf.fit(X_train, y_train)
            train_score = clf.score(X_train, y_train)
            test_score = clf.score(X_test, y_test)
            print(
                f"{hp_label}: {k}, Train Score: {train_score:.4f}, Test Score: {test_score:.4f}"
            )
            results.append((k, train_score, test_score))

    else:
        print("Please choose either 'DTC' or 'KNN' for the algorithm.")
        return

    hyperparameters = [item[0] for item in results]
    train_scores = [item[1] for item in results]
    test_scores = [item[2] for item in results]

    task_4_plotting(
        hyperparameters, train_scores, test_scores, hp_label, which_algorithm
    )


"""
Task 5
"""


def task_5(file_name: str):
    exclusion_cols = ["meal_name", "Benefit", "Target Muscle Group", "Gender"]

    def remove_cols(df: pd.DataFrame, cols_to_rm: list) -> pd.DataFrame:
        df = df.drop(columns=cols_to_rm, axis=1)
        return df

    df = pd.read_csv(file_name)
    df = isolate_non_numeric(df)
    df = remove_cols(df, exclusion_cols)

    df_encoded = df.copy()

    for (
        col
    ) in df_encoded.columns.to_list():  # Counting occurences for frequency encoding
        freq = df_encoded[col].value_counts().to_dict()
        df_encoded[col] = df_encoded[col].map(freq)

    scaler = StandardScaler()
    scaled_features = scaler.fit_transform(df_encoded)

    pca = PCA(n_components=2)
    principal_components = pca.fit_transform(scaled_features)
    principal_df = pd.DataFrame(principal_components, columns=["PC1", "PC2"])

    kalg = KMeans(n_clusters=3, n_init="auto", random_state=42)
    clusters = kalg.fit(principal_df)
    principal_df["Cluster"] = clusters.labels_

    plt.figure(figsize=(10, 6))
    scatter = plt.scatter(
        principal_components[:, 0],
        principal_components[:, 1],
        c=kalg.labels_,
        cmap="viridis",
        alpha=0.7,
    )
    plt.colorbar(scatter)

    plt.title("K-Means Clustering on PCA-Reduced Health Data")
    plt.xlabel(f"Principal Component 1{pca.explained_variance_ratio_[0]:.2%} Variance")
    plt.ylabel(f"Principal Component 2 {pca.explained_variance_ratio_[1]:.2%} Variance")
    plt.show()


"""
Task 6
"""


def task_6(file_name: str):
    """
    Task-6 (Health Dataset): This task involves assessing the relationships between individual
    numerical features within the Health dataset. The goal is to create a list of DataFrames
    named Significant_features.
    The procedure is as follows:
    For each numerical feature F:
    (a) Identify all other features that have a strong or very strong correlation with F.
    (b) If the number of such correlated features is fewer than 4, discard F. Otherwise, create a
    new DataFrame containing F and all its correlated features, and append this DataFrame
    to the Significant_features list.
    Upon completion, the Significant_features list contains DataFrames where each key feature
    F has at least four other strongly or very strongly correlated features.
    For each DataFrame in Significant_features:
    (a) Train a regression model, using the key feature F as the target and the remaining features
    as predictors.
    (b) Calculate the model’s training and test accuracy.
    Visualization:
    Finally, sort the Significant_features list by the size of the DataFrames (from smallest to
    largest, size here refers to the number of columns) and create an appropriate visual-
    ization to clearly display the training and test accuracy for each model. The figure should be
    unambiguous and effectively communicate the performance results
    """
    df = pd.read_csv(file_name)
    df = isolate_numeric(df)

    significant_features = []

    feature_cols = df.columns.to_list()

    for col in feature_cols:
        feature_series = df[col]
        other_features = df.drop(columns=[col])
        for individual_feature in other_features.columns.to_list():
            other_series = other_features[individual_feature]
            r, p_val = pearsonr(feature_series, other_series)
            print(
                f"Correlation between {col} and {individual_feature}: r={r:.4f}, p={p_val:.4f}"
            )
            if abs(r) >= 0.7:
                correlated_features = other_features.loc[
                    :, abs(other_features.corrwith(feature_series)) >= 0.7
                ]
                if correlated_features.shape[1] >= 4:
                    relevant_df = pd.concat(
                        [feature_series, correlated_features], axis=1
                    )
                    significant_features.append(relevant_df)
                break


"""
Task 7 - custom task

Task-7 (Loan Dataset): 
Decision Tree Loan Default Prediction 

Concept and objective:
Uisng a decision tree classifier algorithm I will predict whether or not someone will pay a loan back. I will focus on
loan and borrwer characteristics to predict this and use the three and feature importance to explain which factors drive default risk in
a simple and visual way. This is relevant to the industry as credit officers need to make quick and accurate underwriting decisions.

Target = l

Visual – Feature importance bar chart:
"""

"""
Main Output
"""


def task_7(file_name: str):
    numeric_columns = [
        "age",
        "annual_income",
        "monthly_income",
        "debt_to_income_ratio",
        "credit_score",
        "loan_amount",
        "interest_rate",
        "loan_term",
        "installment",
        "num_of_open_accounts",
        "total_credit_limit",
        "current_balance",
        "public_records",
        "num_of_delinquencies",
        "loan_paid_back",
    ]
    categorical_columns = [
        "gender",
        "marital_status",
        "education_level",
        "employment_status",
        "loan_purpose",
        "grade_subgrade",
        "delinquency_history",
    ]

    target_column = "loan_paid_back"

    df = pd.read_csv(file_name)

    selected_df = df[numeric_columns + categorical_columns].copy()

    df[target_column] = pd.cut(
        df[target_column], bins=2, labels=[0, 1], include_lowest=True
    )

    features = selected_df.drop(columns=[target_column])
    target = df[target_column]

    X_train, X_test, y_train, y_test = train_test_split(
        features, target, test_size=0.2, random_state=42
    )

    clf = tree.DecisionTreeClassifier(max_depth=5, random_state=42)
    clf.fit(X_train, y_train)
    clf.score(X_test, y_test)

    # Display accuracy for proof of working decision tree

    print(f"Decision Tree Classifier Test Accuracy: {clf.score(X_test, y_test):.4f}")

    # Bar chart for feature importance

    feature_importances = clf.feature_importances_
    feature_names = features.columns
    importance_series = pd.Series(feature_importances, index=feature_names)
    sorted_importance = importance_series.sort_values(ascending=False)
    plt.figure(figsize=(10, 6))
    sorted_importance.plot(kind="bar")
    plt.title("Feature Importance in Loan Default Prediction")
    plt.xlabel("Features")
    plt.ylabel("Importance Score")
    plt.show()


def main():
    health_file = "health.csv"
    loan_file = "loan.csv"
    choice = int(input("Enter a task to run (1-7-tasks,8-tests):  "))
    match choice:
        case 1:
            print(f"Task 1\n{'='*40}\n")
            task_1(health_file)
        case 2:
            print(f"Task 2\n{'='*40}\n")
            task_2(health_file)
        case 3:
            print(f"Task 3\n{'='*40}\n")
            task_3(health_file)
        case 4:
            print(f"Task 4\n{'='*40}\n")
            algorithm_choice = input("Which algorithm to use (dtc/knn): ")
            task_4(loan_file, algorithm_choice)
        case 5:
            print(f"Task 5\n{'='*40}\n")
            task_5(health_file)
        case 6:
            print(f"Task 6\n{'='*40}\n")
            task_6(health_file)
        case 7:
            print(f"Task 7\n{'='*40}\n")
            task_7(loan_file)
        case 8:
            print(f"Test out non-numeric isolation\n{'='*40}\n")
            test_isolate_non_numeric()
        case 9:
            print(f"Test out numeric isolation\n{'='*40}\n")
            test_isolate_numeric()

        case _:
            print("Invalid task")


if __name__ == "__main__":
    main()

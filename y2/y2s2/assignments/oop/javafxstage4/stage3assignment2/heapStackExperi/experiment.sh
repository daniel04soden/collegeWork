javac ./Main.java

echo "Welcome to the Java Assignment2 experiment here we will test out how mem allocation affects heaps and stacks"

echo "Which would you like to test?"
read -p "Enter your choice (heap/stack)(1/2): " experiment_type

echo "Choose your size"
read -p "Enter your choice (Small/large)(1/2): " size


case "$experiment_type" in
    1)
        case "$size" in
            1)
                echo "Starting experiment"
                java -Xms256m -Xmx512m Main
                ;;
            2)
                echo "Starting experiment"
                java -Xms4g -Xmx8g Main
                ;;
            *)
                echo"Invalid choice"
                exit 1
                ;;
        esac
        cat heapExperimentData.csv
        ;;
    2)
        case "$size" in
            1)
                echo "Starting experiment"
                java -Xss10m Main
                ;;
            2)
                echo "Starting experiment"
                java -Xss4G Main
                ;;
            *)
                echo"Invalid choice"
                exit 1
                ;;
        esac
        cat stackExperimentData.csv
        ;;
    *)
    echo"Invalid choice"
    exit 1
    ;;

esac

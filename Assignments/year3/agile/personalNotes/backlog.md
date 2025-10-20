# [TODO]{.todo .TODO} Sprint 1 - Organising and Parsing the data \[\] {#sprint-1---organising-and-parsing-the-data}

- \[\] Remove monthyear Column
  - \[\] Once monthyear deleted, split date and time -\[\] Time needs
    seconds taken out as always XX:00
- \[\] Overall, combine two csv files into one with unique identifiers
  as each row
  - \[\] Create column for if still in/out ie outcome may be null - if
    not in outcome, have boolean for in shelter still
  - This will need a lot of evaluation of both files, many cells/entries
    may be removed as some with outcomes may not be included in intakes
    and vice versa
- \[\] Get rid of names, irrelevant to trends and chatbot info, will
  take up processing power
- \[\] Age can be removed, date of birth infers and can calculate
  current age as time goes on
- \[\] For any species labelled as other, replace other with breed entry
  - If the Other breed includes mix, we leave breed as mix and species
    as animal
  - Otherwise itd be like bat bat
- \[\] Add separate column for being spayed/neutered or not - boolean as
  spay/neuter is inferred by gender
- \[\] Remove sex upon outcome, sex will not change intime

# [TODO]{.todo .TODO} Sprint 2 - Training the LLM on the data {#sprint-2---training-the-llm-on-the-data}

- \[\] Decide on if file conversion needed
- \[\] Decide on if open source model and if so what model
- \[\] Train it

# [TODO]{.todo .TODO} Sprint 3 - Analayze the data using the LLM {#sprint-3---analayze-the-data-using-the-llm}

- \[\] Tweak the LLM to analyze the data in a specific way
- \[\] Decide on how we want the LLM to respond with - markdown,json,
  csv etc

# [TODO]{.todo .TODO} Sprint 4 - Create user interface for displaying results and chatting {#sprint-4---create-user-interface-for-displaying-results-and-chatting}

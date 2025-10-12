# DanFawGavBen Product backlog

- This document acts as a means of keeping track in a todo list format what tasks we each need to individually work on.


# Products in use so far

- Github
- Trello (Jira is cringe)

## Daniel Product Backlog - ML

### Tech stack

- Language - Python
- Framework - Tensorflow/Keras,CrewAI, OpenAI Platform

#### Resources

- Use Python as the base language for flexibility and strong ecosystem.
- Develop speech feature extraction, classification, and augmentation pipelines in TensorFlow/Keras.
- Wrap these ML components inside a CrewAI or AutoGen multi-agent system for modularity, scalability, and integration of external data/tools (e.g., multimodal diagnosis, clinical validation).
- CrewAI is recommended if specialized agent collaboration, workflow memory, and scalability to other diagnosis modalities are needed.

### Product Backlog
#### Crewai work

- [ ] Retrieve news relating to dimentia and send it to ui elements as json - news is patient focused
  - [ ] Medical Researcher agent - checks recent news sources for dementia related news of interest to medical professionals
  - [ ] Patient Researcher eagent - checks patient specific new sources for dementia related news of interest to carers and thoe suffering from dementia
  - [ ] Risk calculator agent - Uses the patient data based on speech patterns in order to determine whether or not someone has dementia or not
 - [ ] Risk assesment - reformats data in order to report back on result in a user friendy way
    
#### TensorFlow work
 - [ ] Risk predication through the speeces and questionaire results from nms web and mobile apps

## Fawaz Product Backlog - Backend/Db

### Tech stack

- Database - SQLite
- Language/Framework - Golang + Fiber 

### Product Backlog

- [ ] **Research and pick out exact database**
- [ ] **User table** - Different users, Admin,doctors,patients
- [ ] **Docker containers for database**
- [ ] API Linkinng mobile app and web app - Done much later upon completion of other aspects
- [ ] Store date time duration of tests being taken


## Ben  - Mobile

### Tech stack

- Langugages/Frameworks:

  - Frontend - Kotlin Jetpack compose
  - Backend - Kotlin+Springboot - Solid backend Framework batteries included
##### Resources:

- https://developer.android.com/courses/jetpack-compose/course
- https://developer.android.com/courses/android-basics-compose/course
- https://spring.io/guides/tutorials/spring-boot-kotlin


### Product Backlog
- [ ] **Account Class** - need to get table from Fawaaz first for fields - id,name,password,bcrypt etc
- [ ] **Register frontend**
- [ ] **Login Frontend**
- [ ] **Home Frontend** - Includes dead links for now to all features
- [ ] Register logic
- [ ] Login logic - I would reccomend MVC pattern for this tbh
- [ ] Forget pass logic  
- [ ] 2fa


- [ ] Questionnaire Class 
- [ ] Questionnaire frontend 
- [ ] Questionnaire backend sending data 

- [ ] Classes to store speech data.

- [ ] Speech to text api  - Speak for 1 minute about image displayed
  - [ ] If possible, have microphone test to ensure no issues.
- [ ] Cognitive mini tests - Prompt user to take test on login periodically???
- [ ] Support ticket frontend - to devs
- [ ] Support ticket backend - to devs
- [ ] Support ticket frontend - to healthcare providers
- [ ] Support ticket backend - to healthcare providers

- [ ] Dementia score tile at home menu - links to summary from all completed tests
  - find some kinda of graphing library for kotlin - if not link in python images
- [ ] Ratings and Reviews - Simple api call to send user to play store 

## Gavin Product Backlog - Frontend

### Tech stack

- Frontend - React (Typescript) + tailwind
- Backend - Nextjs (Typescript)

### Product Backlog

- [ ] **Account Type** - eg id,name,password,bcrypt etc
- [ ] **Register frontend**
- [ ] **Login Frontend**
- [ ] **Home Frontend** - Different depending on user type - admin,doctor,patient 
- [ ] Auth+cookies - users can't access much of site until logged in like server side 
- [ ] Forget pass logic  

- [ ] Patient Profile pages - Include pfp, bio etc
- [ ] Doctor Profile pages - Include pfp, bio etc
- [ ] Risk dashboard - store visual scores from mobile app
- [ ] Reports - Tile on doctor portal showing list of their patients and brief report on their risk profiles
- [  ] Captcha
- [  ] 2fa

- [ ] Admin portal - Includes CRUD operations for patients & doctors 
- [ ] Data aggregation -  Ability for admins to extend training data

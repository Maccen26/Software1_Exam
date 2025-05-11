# README

This file contains instructions on running the program via the UI and runnning the tests trough the terminal.


## Running the tests

You can run the tests using ``mvn clean test`` or through your IDE.

## Running the application
### Background 
The applications start up with several activites, developers and projects such that the user can test several functionalities 

The following developers is contained in the app when launching the app. 

1. huba 
2. tf
3. mhh 
3. lkm 
4. fbj
5. jkv


The following project is contained in the app with the following project leader
1. 20251 - project leader: huba
2. 20252 - project leader: huba
3. 20253 - project leader: huba

The following activites is present in the app for every developer. 
1. 20251 -> Activit1, Activity2, Activity3
2. 20252 -> Activity1, Activity2
3. 20253 -> Activity1



### Starting the application
#### IDE
You can run the application through the IDE in the class "Ui" at the path src/main/java/dtu/ui/Ui.java. 

#### Terminal

You can run the application through the terminal first executing 

1. ``mvn clean package`` 

and then executing 

2. ``java -jar target/ExamProject-SNAPSHOT-1.0.jar``

## How to navigate the UI

### Login: 
1. Login with one of the contained developers

### Main Page. 
The main page shows all of the activities. They are color coded given their project. 

### Main page -> Activity Page. 
1. Double click on one of the displayed activites

### Main page -> Project overview page: 
1. Mouse over the top left Account figure
2. press project from the drop down menu 

### Project overview page -> Project Page
1. Press one of the projects 

### Project page -> Activity Page
1. Press one of the activites within the project

## How to perform actions in the UI

### Create projects
Mouse over the "+" icon in the top right corner at the project overview page 

### Project Mangagement actions 
Go into a project from the project overview page. 
Mouse over the "+" icon in the top right corner to see avaliable actions 

### Activity Mangement actions 
Go into a activity from either the main page or the project page. 
Mouse over the "+" icon in the top right corner to see avaliable actions 

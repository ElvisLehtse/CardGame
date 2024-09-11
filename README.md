## Installation:
### Setting up Spring Boot application

1. Make sure you have installed Java JDK 21.  
2. Install Intellij IDEA Commmunity edition  
3. Import the Maven project  

Make sure your JAVA_HOME variable is set up to point to your Java 21 installation in that case

### Setting up PostgreSQL

1. Download and install the latest PostgreSQL  
2. Under System Properties, got to Environment Variables  
3. Double-click on Variable Path under User variables  
4. Add your PostgreSQL\{YOUR VERSION HERE}\bin to the variable:  
   Should looks something like this: C:\Program Files\PostgreSQL\16\bin  

### Setting up GitBash

1. Download and install GitBash to your machine  

### Running ShellScript file

1. Open your HiLoCardGame directory  
2. Open GitBash in the directory  
   Right-click on empty space -> Open Git Bash here  
3. Type sh "CardGameInstall.sh" inside the terminal without ""  
4. Enter your PostgreSQL password and database name  

### Running the Spring Boot application

Run the HiLoCardGameApplication from IDE.  

### Running the front-end

1. Make sure you have NodeJS version 18+ installed.  
2. Open the terminal.  
3. Navigate to frontend project cd frontend/  
4. Start the development server npm start. Frontend runs on port 4200, so make sure it's not in use.  

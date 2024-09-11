#!/bin/bash
 
# Variables
FRONTEND_DIR="./cardgame-front"   
#DB_NAME="cardgame"                
DB_USER="postgres"            
DB_HOST="localhost"                         
DB_PORT="5432"                             
APP_PROPERTIES="../src/main/resources/application.properties"  
 
# Step 1: Ask for the PostgreSQL password
echo "Please enter your PostgreSQL password: "
read -s DB_PASSWORD    # -s for silent input (password will not be shown)

# Step 1b: Ask for the PostgreSQL database name
echo "Please enter your PostgreSQL database name: "
read DB_NAME
 
# Step 2: Install Node Modules
cd $FRONTEND_DIR
echo "Installing node_modules for front-end..."
npm install
 
# Step 3: Create PostgreSQL Database
echo "Creating PostgreSQL database..."
# Log in to PostgreSQL and create the database, passing the password using PGPASSWORD
export PGPASSWORD=$DB_PASSWORD
psql -U $DB_USER -h $DB_HOST -p $DB_PORT -c "CREATE DATABASE $DB_NAME;"
 
# Step 4: Insert database credentials into application.properties
echo "Updating application.properties with database credentials..."
 
# Check if application.properties exists
if [ -f "$APP_PROPERTIES" ]; then
    # Write database properties into the application.properties file
   cat > $APP_PROPERTIES <<EOL
spring.application.name=HiLoCardGame
spring.datasource.url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME
spring.datasource.username=$DB_USER
spring.datasource.password=$DB_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver
EOL
 
    echo "application.properties updated successfully."
else
    echo "Error: application.properties file not found."
fi
 
# Unset the password for security
unset PGPASSWORD
 
echo "Script execution completed."
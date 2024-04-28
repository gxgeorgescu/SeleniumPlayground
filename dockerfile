# Use the Maven Chrome JDK 17 image as the base image
FROM markhobson/maven-chrome:jdk-17
  
# Display Chrome and ChromeDriver versions
RUN google-chrome --version
RUN chromedriver --version
  
# Set the working directory in the container
WORKDIR /usr/src/app
  
# Copy the project files into the container
COPY . .
  
# Run Maven clean test command
CMD ["mvn", "clean", "test"]
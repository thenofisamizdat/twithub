# Twithub
TwitHub is a Spring Boot Java application with an AngularJS client.
This application was built by Neil Byrne for Sportdec using JHipster 4.6.2, you can find documentation and help at [https://jhipster.github.io/documentation-archive/v4.6.2](https://jhipster.github.io/documentation-archive/v4.6.2).

## Live Application

The application is live in production mode at 
http://ec2-34-253-215-85.eu-west-1.compute.amazonaws.com
## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.
2. [Yarn][]: We use Yarn to manage Node dependencies.
   Depending on your system, you can install Yarn either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    yarn install

We use [Gulp][] as our build system. Install the Gulp command-line tool globally with:

    yarn global add gulp-cli

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    gulp

[Bower][] is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [bower.json](bower.json). You can also run `bower update` and `bower install` to manage dependencies.
Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].


## Building for production

To optimize the twithub application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

## Database Connectivity

The application is set to connect to a Sandbox AWS database (in prod profile). 

In dev the app uses an in-memory database.


## Building wES from source

**wES** is built with [Gradle](http://gradle.org/) on JDK8, targeting Java 1.8. You don't have to install anything, the only prerequisites are [Git](http://help.github.com/set-up-git-redirect) and Java JDK.

### Check out sources

Simply clone **wES** Git repo:

    git clone https://github.com/DataSays/wES.git wES

### Compile and test, build jars

You can build the wES project with:

    gradlew build

This will build all jars and run all unit tests.
To skip the tests (for faster build), execute:

    gradlew build -x test
    
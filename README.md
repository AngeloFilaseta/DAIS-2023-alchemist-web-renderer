# DAIS-2023-alchemist-web-renderer

## Prerequisites
This project requires a Java version (>=11) capable of executing Gradle.  
If you have no Java installed, it is recommended to install the latest LTS Java from AdoptOpenJDK (unless a new LTS is
out and Gradle does not yet support it).

## Execution
UNIX users can automatically launch a working instance of the application by executing this single command on a shell.
```shell
curl https://raw.githubusercontent.com/AngeloFilaseta/DAIS-2023-alchemist-web-renderer/master/.github/run.sh | bash
```
It is still possible to manually execute the application by cloning this repository and executing the `run` 
task via [Gradle](https://gradle.org/).
```shell
git clone https://github.com/AngeloFilaseta/DAIS-2023-alchemist-web-renderer.git
cd DAIS-2023-alchemist-web-renderer
./gradlew run # UNIX & Bash Rmulators
gradlew.bat run # Windows
```

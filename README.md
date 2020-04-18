As a brief exercise I made a naïve solution for [24 Game](https://en.wikipedia.org/wiki/24_Game). My first encounter with the puzzle was [Maunulan pulmat](https://www.youtube.com/watch?v=Vf21lu10UHo) (in Finnish). Result comes in [Polish/prefix notation](https://en.wikipedia.org/wiki/Polish_notation).

To run, you need Maven. (Maven setup adapted from [kotlin-examples](https://github.com/Kotlin/kotlin-examples/tree/master/maven/hello-world))

To execute:

    mvn compile exec:java -Dexec.args="24.0 1.0 9.0 7.0 4.0"

To run the smoke tests:

    mvn test

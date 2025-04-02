# f.csa311-assignment1
F.CSA311 хичээлийн бие даалт 1.

Flashcard Application - Command Line Usage
========================================

Usage: java -jar target\flashcard-app-1.0-SNAPSHOT.jar <cards-file> [options]

Required Arguments:
  <cards-file>          Path to file containing flashcard data (Q&A pairs)

Options:
  --help                Show this help message and exit
  --order <order>       Set card sorting order
                        Default: random
                        Choices: [random, worst-first, recent-mistakes-first]
  --repetitions <num>   Number of times each card must be answered correctly
                        Default: 1 (no repetition)
  --invertCards         Display answers as questions and vice versa
                        Default: false (normal display)

Examples:
  Basic usage:
    java -jar flashcard-app.jar cards.txt
  
  Advanced usage:
    java -jar flashcard-app.jar cards.txt --order worst-first --repetitions 3
    java -jar flashcard-app.jar mycards.txt --invertCards

File Format:
  cards.txt should contain question-answer pairs separated by |
  Example line: "Capital of France|Paris"
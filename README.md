# f.csa311-assignment1
F.CSA311 хичээлийн бие даалт 1.

# Flashcard Application - Command Line Usage

This command-line flashcard application helps you practice question-answer pairs from a file.

## Usage

```sh
java -jar target/flashcard-app-1.0-SNAPSHOT.jar <cards-file> [options]
```

### Required Argument

- `<cards-file>`: Path to the file containing flashcard data (Q&A pairs).

### Options

- `--help`  
  Show this help message and exit.

- `--order <order>`  
  Set the sorting order for flashcards.  
  **Default:** `random`  
  **Choices:** `random`, `worst-first`, `recent-mistakes-first`

- `--repetitions <num>`  
  Number of times each card must be answered correctly before it is removed from the queue.  
  **Default:** `1` (no repetition)

- `--invertCards`  
  Display answers as questions and vice versa.  
  **Default:** `false` (normal display)

## Examples

Basic usage:
```sh
java -jar flashcard-app.jar cards.txt
```

Advanced usage:
```sh
java -jar flashcard-app.jar cards.txt --order worst-first --repetitions 3
java -jar flashcard-app.jar mycards.txt --invertCards
```

## File Format

The flashcard file should contain question-answer pairs separated by `|`.  
Each line should be formatted as follows:

```
Question|Answer
```

### Example:
```
Capital of France|Paris
Largest planet in the Solar System|Jupiter
```

---

Happy studying! 📚
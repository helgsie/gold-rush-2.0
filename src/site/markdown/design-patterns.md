## Design Patterns

### Observer Pattern

The Observer pattern is used in GoldRush 2.0 to keep the user interface in sync with the game's score system without coupling the two components directly together.

### Roles

| Role | Class |
| --- | --- |
| Subject | `Stigakerfi` |
| Observer interface | `StigaListener` |
| Concrete observer | `GoldController` |

### How it works

`Stigakerfi` keeps track of the current score and a list of registered `StigaListener` observers. Every time the score changes, it notifies all registered observers of the update.

`GoldController` implements `StigaListener` and registers itself with `Stigakerfi`. When it receives a notification about a score change, it updates the score label in the user interface.

This keeps the score logic fully separated from the UI. `Stigakerfi` knows nothing about JavaFX or how the score is displayed and `GoldController` does not need to constantly check for changes. It simply reacts when a notification arrives.
# UML Class Diagram

```mermaid
classDiagram

    %% Business Logic
    class Leikur {
        -selectedCharacter: String
        -erfidleikastig: Erfidleikastig
        +setSelectedCharacter(String) void
        +getSelectedCharacter() String
        +setErfidleikastig(Erfidleikastig) void
        +getErfidleikastig() Erfidleikastig
        +baetaVidStigum(int) void
        +getStigin() int
        +getHaestuStig() int
        +endurstillaStig() void
    }
    class Stigakerfi {
        -currentScore: int
        -highScore: int
        +addPoints(int) void
        +resetCurrentScore() void
        +getCurrentScore() int
        +getHighScore() int
    }
    class Klukka {
        -currentTimeInSeconds: int
        -running: boolean
        +startCountUp() void
        +tick() void
        +stopCountUp() void
        +getFormattedTime() String
    }
    class Erfidleikastig {
        <<enumeration>>
        AUDVELT
        MIDLUNGS
        ERFITT
        +getFjoldiOvina() int
    }
    class LeikStada {
        <<enumeration>>
        EKKI_BYRJADUR
        I_GANGI
        LOKID
    }

    %% Game Entities
    class GoldController {
        -leikur: Leikur
        -klukka: Klukka
        +setLeikur(Leikur) void
        +updatePoints(int) void
        +leikLokid(String) void
        +hreinsaBord() void
    }
    class Leikbord {
        -leikur: Leikur
        -grafari: Grafari
        -gulls: List~Gull~
        -ovinur: ObservableList~Ovinur~
        +hefjaAfram() void
        +hreinsaBord() void
        +grafaGull() void
        +ovinurDrepur() void
    }
    class Grafari {
        +setImage(String) void
    }
    class Gull
    class Ovinur {
        -timer: Timer
        +stop() void
        +isCollidingWithGrafari(Grafari) boolean
        +afram() void
    }

    %% Inheritance
    Grafari --|> Rectangle
    Gull --|> Rectangle
    Ovinur --|> Rectangle
    Leikbord --|> Pane

    %% Composition
    Leikur *-- Stigakerfi
    GoldController *-- Leikbord
    GoldController *-- Klukka

    %% Associations
    Leikur --> Erfidleikastig
    GoldController --> Leikur
    Leikbord --> Leikur
    Leikbord --> Grafari
    Leikbord --> Gull
    Leikbord --> Ovinur
    Ovinur --> Grafari
```
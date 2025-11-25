# Daniel Soden Distributed Systems Assignment - Spelling Bee gRPC + Design Patterns

## Table of contents

- [Design Pattern 1 - Builder pattern](#design-pattern-1---builder-pattern)
- [Design Pattern 2&3 - Factory and Decorator](#design-pattern-2&3---factory-and-decorator)
- [Design Pattern 4 - Singleton](#design-pattern-4---singleton)
- [Conclusion/Issues](#conclusionissues)

## Design Pattern 1 - Builder pattern

- When processing user entry and generating pangrams there was a multitude of restrictions that would break the conditions of being a valid entry or pangram.
- Given these restrictions, we can use the builder pattern to create a builder that can be used to build a valid entry or pangram by returning nil if ever these restrictions are broken. In the main implementation of these structs, if they are ever nil, the function will know to handle it effectively and build again.

### Entry Builder

- To be a valid entry, it needs to:

1. Have the middle letter of the pangram
2. Be 4 characters minimum
3. Be a valid English word (using provided dictionary)

#### entryBuilder.go

```go

type EntryBuilder interface {
	SetLetters(letters string, p Pangram, d data.WordDictionary) EntryBuilder
	SetLength(letters string) EntryBuilder
	Build() *Entry
}

type entryBuilder struct {
	entry *Entry
}

type EntryDirector struct {
	EntryBuilder EntryBuilder
}

func (eD *EntryDirector) ConstructEntry(letters string, p Pangram, w data.WordDictionary) *Entry {
	if eD.EntryBuilder == nil {
		fmt.Println("Invalid entry, try play again & re-read the rules")
		return nil
	} else {
		eD.EntryBuilder.SetLetters(letters, p, w)
		eD.EntryBuilder.SetLength(letters)
		return eD.EntryBuilder.Build()
	}
}

func NewEntryBuilder() EntryBuilder {
	return &entryBuilder{
		entry: &Entry{},
	}
}

func (eb *entryBuilder) SetLetters(letters string, p Pangram, words data.WordDictionary) EntryBuilder {
	if !isLetter(letters) {
		fmt.Println("Invalid entry, must only be alpha chars ,try again next time")
		return nil
	} else if !strings.Contains(strings.ToLower(letters), p.MiddleVal) {
		fmt.Printf("Invalid entry must contain letter %s\n", p.MiddleVal)
		return nil
	} else if !(data.ValidateWord(letters, words)) {
		fmt.Println("Invalid entry, not a real word")
		return nil
	} else {
		eb.entry.Letters = letters
		return eb
	}
}

func (eb *entryBuilder) SetLength(letters string) EntryBuilder {
	lenLetters := len(letters)
	if lenLetters < 4 {
		fmt.Println("Word too short try again next time")
		eb.entry.Length = 0
		return eb
	} else {
		eb.entry.Length = lenLetters
		return eb
	}
}

func (eb *entryBuilder) Build() *Entry {
	return eb.entry
}
```

#### bee.go - example of entry builder usage

- As we can see we declare our builder, pass the builder into the director and then call the construct method on the director.
- All of the functions called in construction method can either return an entrybuilder as normal to be put together for the final construction or nil and if any are nil,this is handled by setting the points to 0

```go
func ProcessEntry(userInput string, p *entity.Pangram, validWords data.WordDictionary) (points int) {
	// Construct entry to ensure that it is valid
	entryBuilder := entity.NewEntryBuilder()
	entryDirector := &entity.EntryDirector{EntryBuilder: entryBuilder}
	entry := entryDirector.ConstructEntry(userInput, *p, validWords)

	if entry != nil {
		points = getPoints(entry.Letters, p.Letters)
	} else {
		points = 0
	}
	return
}
```

### Pangram Builder

#### pangramBuilder.go

```go
func (pD *PangramDirector) ConstructPangram(letters string) *Pangram {
	if pD.PangramBuilder == nil { // If ever returned as nil with various setters, not constructed
		fmt.Println("Invalid pangram, try play again & re-read the rules")
		return nil
	}
	pD.PangramBuilder.SetLetters(letters)
	pD.PangramBuilder.SetLength(letters)
	pD.PangramBuilder.SetMiddleVal(letters)

	return pD.PangramBuilder.Build()
}

func NewPangramBuilder() PangramBuilder {
	return &pangramBuilder{
		pangram: &Pangram{},
	}
}


func (pb *pangramBuilder) SetLetters(letters string) PangramBuilder {
	if !verifyPangram(letters) {
		fmt.Printf("Invalid pangram must contain letter s\n")
		return nil
	} else {
		pb.pangram.Letters = letters
		return pb
	}
}

func (pb *pangramBuilder) SetLength(letters string) PangramBuilder {
	lenLetters := len(letters)
	if lenLetters < 4 {
		fmt.Println("Word too short try again next time")
		return nil
	} else {
		pb.pangram.Length = lenLetters
		return pb
	}
}

func (pb *pangramBuilder) SetMiddleVal(letters string) PangramBuilder {
	if len(letters) < 4 {
		fmt.Println("Word too short try again next time")
		return nil
	} else {
		res := []rune(letters)
		length := len(res)
		middle := (length / 2) - 1 // works well for both odd and even
		pb.pangram.MiddleVal = string(res[middle])
		return pb
	}
}

func (pb *pangramBuilder) Build() *Pangram {
	return pb.pangram
}
```

#### bee.go - example of pangram builder usage

- This is a lot simpler as we simply see it being constructed from a randomly generated pangram, being passed into the construct pangram function and being returned.
- Pangram is the most difficult due to its main conditions:

1. All letters must be unique
2. It must 7 characters in length
3. It cannot have the letter 's' in the word

- These restrictions are handled by the setLetters function, which checks said conditions and, if it is all okay, it will return a builder, otherwise the function will return nil.

```go
func GeneratePangram(dictPath string) (p *entity.Pangram) {
	pangramStr := data.GetRandomPangram(dictPath)
	pangramBuilder := entity.NewPangramBuilder()
	pangramDirector := &entity.PangramDirector{PangramBuilder: pangramBuilder}
	pangram := pangramDirector.ConstructPangram(pangramStr)

	return pangram
}
```

## Design Pattern 2&3 - Factory and Decorator

- In this assignment we were tasked with making a spelling bee **game**. In said game we were given a set of strict rules thus a variation of games initially to me made no sense
- That was until in one of the labs we implemented decorators as seen below, where we are able to create a variation on the output coming from an interface's methods implementation by simply passing said interface into another method
- To introduce the options of playing with the logging decoration, the factory pattern was needed as seen below:

```go

func New(kind string) (Game, error) {
	switch kind {
	case "standard":
		return Standard{}, nil
	case "standard+logging":
		s := Standard{}
		return decorate.WrapLogging(s), nil
	default:
		return nil, fmt.Errorf("unknown game kind %s", kind)
	}
}
```

- Here we simply pass in the name for a kind of game ie standard or standard and logging and we return the interface for the chosen kind.
- standard just returns the standard object whereas standard+logging wraps said interface in this additional functionality:

```go

type Game interface {
	Type() string
	Play() (int, []string)
}

type Logging struct {
	Inner Game
}

func (d Logging) Type() string { return d.Inner.Type() }

func (d Logging) Play() (int, []string) {
	point, guess := d.Inner.Play()
	log.Printf("[LOG] %s → Total Points %d", d.Type(), point)
	for i := range guess {
		log.Printf("[LOG] Guess No. %d → %s", i, guess[i])
	}
	return point, guess
}

func WrapLogging(g Game) Game { return Logging{g} }
```

- We re-define games here and introduce an inner interface in our logging struct which uses the game.Play() method and also adds a log for specific metrics such as words guessed and the points we have earned at different parts of the game

## Design Pattern 4 - Singleton

- In our code to manage the creation of games we used a manager instance as a means of getting information about said game and passing it through grpc.

- However if one were to have multiple managers we may run into issues where a game is ongoing, we are accessing information related to the game on one manager and the game has been updated and new information has arose and we are accessing information related to the game on another manager.
- To solve this issue we can use the singleton pattern to ensure that only one instance of a game is created and that all information is accessed through said instance.

```go
func Get() Manager {
	once.Do(func() {
		instance = &mgr{
			items: make(map[string]games.Game),
		}
	})
	return instance
}
```

- As seen above we have a publicly accesible function Get which returns a manager. In this manager function we retrieve the address of mgr which stores the various ongoing games
- Manager is able to create games as seen below and we are able to prevent the creation of multiple users accessing our manager by using a mutex lock.

```go
func (m *mgr) Create(kind string) (string, games.Game, error) {
	g, err := games.New(kind)
	if err != nil {
		return "", nil, err
	}

	m.mu.Lock()
	m.nextID++
	id := fmt.Sprintf("game-%d", m.nextID)
	m.items[id] = g

	defer m.mu.Unlock()

	return id, g, nil
}

func (m *mgr) Get(id string) (games.Game, bool) {
	m.mu.RLock()
	defer m.mu.RUnlock()
	g, ok := m.items[id]

	return g, ok
}
```

# Conclusion/Issues

- I learned a lot throughout this project about certain design patterns and how they are implemented practically instead of for very little reason in certain object oriented languages. On top of this I was able to explore gRPC and send messages across two "machines".

## Issues

- The primary issue that arose for me that I was never able to fully resolve was mainly to do with grpc. I ended up modelling the system very similarly to the dice game we had practiced in class however compared to this game where you are providing a multitude of inputs, the dice game has one request and one result. This game has a larger quantity of checks and overall complexity. The main issue I faced was when connecting the client to the server, the code I wanted to be prompted on the clients end ended up being prompted on the server end. My resolution for this would be to re-factor the system to have games tate be saved so that the client is able to send and receive data on the fly as opposed to the final summary being given as of now. Due to time constraints this was not possible but this will be re-factored before assignment 2.

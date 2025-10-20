# Programming for data analytics

- 16th october first examination - on campus

## Python libraries to be used:

- Numpy, pandas, matplotlib etc.

## Pandas learning:

### Starting with pandas

``` python
import pandas as pd

def main():
    pass

if __name__ == "__main__":
    main()
```

### Creating my first data frame

``` python
import pandas as pd

def main():
    df = pd.DataFrame([[1,2,3],[3,4,5],[6,7,8]],columns=["A","B","C"])
    print(df.head())
    print(df.describe())

if __name__ == "__main__":
    main()
```

### Loading in data

``` python
import pandas as pd

def main():
    pd.read_csv('./data/coffee.csv')


```

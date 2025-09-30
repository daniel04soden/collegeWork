# Steps on How to make a Pull request

## 0. Clone the git repo - Only ever do so once

```bash
git clone https://github.com/daniel04soden/group-project

```

## 1. Pull latest main branch - First real step

```bash
git pull  
```
- Or if you're being real strict 

```bash
git pull origin main
```

## 2. Create a branch for your new feature - Important to stop silly conflicts
- When coding your cli prompt should never look as follow
```sh
$ group-project [main*]> 
```
- If so you have made changes in the main branch and will need to move those files to another directory and re-clone the repo or revert to an older version.
- Instead do as follows
    ```bash
    git branch [feature-name]
    git checkout [feature-name]
    ```
- Your cli prompt should now look like this

```sh
$ group-project [feature-name]> 
```

- From here you can make as many changes as you please without worrying about messing up the main branch.
- Keep in mind there may in time if we decide to work collaboratively on certain parts of the project, we may encounter merge conflicts and will need to make decisions on merging,rebasing or deciding with one persons code

## 3. Make your changes. 
- From here you can code as normal and once you've completed your changes, tested they work fine with everything else, you can then move forward.

## 4. Add/remove files 
- In general there are only 2 important git add commands that encompass your changes:

### Git add .
- This will simply add all changes whether they be deleting tracked files, tracking new files or changed code this will be the most common approach.

```bash
git add . 
```

### Git add -p

- This is overall my preferred approach as then we all are individually responsible for looking through the individual diffs throughout the code base before commiting anything

```bash
git add -p 
```

- After staging/adding these changes, it is now time to:

## 5. Commit your changes 

- From here we will commit the code to the branch, in this commit we will provide a brief description of what changes were made, max two sentences I usually format mine with commas if ever multiple changes were made.

```bash
git commit -m "Added x feature to my y" 
```

## 6. Pushing your changes to the branch head

- From here we need to push the changes before the code is available for a pull request. It is another simple command being:
    ```bash
    git push origin [feature-name]
    ```
- If you've ever seen me using git I usually just push to main and have at times for other projects however for this I would prefer we work ina professional like environment as a means of ensuring we are prepared for placement

- Finally these changes are available to all on github in the branch you created. 

## 7. Creating your pr

- From here visit the repo at github.com/daniel04soden/group-project and in the pull request tab hit new pull request.
- Then select the branch you want merged into main, give any further info needed for the person reviewing this code and let us know you've created a pr and if you want it merged sooner or later




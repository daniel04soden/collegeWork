
# Choice:
- Can we allocate this file?
# Constraint:
- Space within hdds array
	- Space of current and is this one full?

# Goal
- Fill up hard drives with given files

# Solution ideas
- Keep in mind final should kinda be like {0,0,1,2,2,2....}
- Back track may be through a -1 
- Separate function for validation
- For that separation, we can do the check if its already full...
- How do we track a middle of allocation hdd - **NOT ALLOWED**
- Main thoughts are += another dupe array of size hdd
- Seems too brute forcey
- and then would our backtrack then be:
	- -= foo
	- foo = -1
- Key consistent backtrack -> if legal/valid true: all good return foo
- Otherwise don't return, go to default but before then, backtrack

## Monday Steps
1. Check if solution found - at the last file index
2. Otherwise not returning, loop over result index
3. If the allocation is -1, allocate to the fileIdx we're looking at 
4. Move to next file
5. Then if it's valid, return res - May need to be changed to a recursive step here..
6. Otherwise move fileIdx back and set it to -1
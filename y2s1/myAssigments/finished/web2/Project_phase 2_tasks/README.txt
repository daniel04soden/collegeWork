This is the project for ServerSideWebDev. It uses HTML, CSS, Javascript, NodeJs, MongoDB/Mongoose

What it can do:
    View entries from mongoDB
    Create a new entry
    Edit an entry
    Delete an entry

What it contains:
    Home page
    Help page
    About page
    New entry page
    View data page
    2 Success pages (for update and delete)

When you make new entry, a script checks if the total inputs
is less than 1140 which is the number of minutes in a day.
It also uses time stamps to show the date when the entry was created.
Im using the .toLocaleDateString() function to shorten the 
timestamp to just show the date.

When you edit an entry, it does the same checks as when making
a new entry. Once you click submit, it leads you to a
"update successful" page, from which you have a big button to
go back to the table page.

When you delete an entry, it takes you to a "delete successful"
page, from which you have a big button to go back to the 
table page.


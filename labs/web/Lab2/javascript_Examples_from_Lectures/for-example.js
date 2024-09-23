var pageNames = [
 "Home",
    "About Us",
    "Contact Us",
    "JavaScript Playground",
    "News",
    "Blog"
    ];

    document ={
        title: 'News',
        description: 'todays update'
    }

    for (i=0; i < 10; i++)
    {
        if (document.title === pageNames[i])
        {
            console.log("We ARE here: " + pageNames[i]);
        }
        else
        {
            console.log
        ("We ARE NOt here so I am " + pageNames[i]);   
        }
    }
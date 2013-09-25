# Fitnesse Wiki Syntax #

[User Guide](http://fitnesse.org/FitNesse.UserGuide)  
[Quick Reference](http://fitnesse.org/FitNesse.UserGuide.QuickReferenceGuide)  

Headers
```
!1
!2
!3
```

    ''italics''
    '''bold'''
    {{{ preformatted       text}}}
    !- literal no formatting -!
    # comments
    
Links    
```
http://google.com

WikiWord

[[word or phrase][WikiPage]]
[[word or phrase][WikiPage#anchorName]]

!anchor anchorName

.#anchorName
```

Lists
```
* Item one
* Item two
 * sub item
 
1 Item one
2 Item two
```

Tables
```
|table|
|column1|column2|
|1|2|

# Use ! for literal tables
!|table|
|column1|column2|
```
    
    !path classpath
    !include WikiPage
    !today
    !lastmodified
    
Collapsible Sections
```
# Start open

!* Section title
content for section
*!

# Start closed
!*> Section title
content for section
*!
```

# Fitnesse startup options #

* p: port number __80__
* d: working directory __.__
* r: page root directory __FitNesseRoot__
* l: log directory
* e: days till versions expire
* a: enable authentication
* c: Command
* f: Config file
* b: output
* o: Omit updates
* i: Install only
* 

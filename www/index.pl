#!/usr/bin/perl
$hora = localtime;
print <<"INDEX";
<html>
    <head>
        <title>teste</title>
    </head>
    <body>
        <h1>$hora</h1>
    </body>
</html>
INDEX
exit;
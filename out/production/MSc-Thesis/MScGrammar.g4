grammar MScGrammar ;
description
    : leadingStatement initialStatement (statementList)? closingStatement ;
leadingStatement
    : 'The following textual description follows the closed-world assumption, meaning that only the activities specified can be executed in the specified order. Any possible activity and execution that is not specified is considered impossible.' NEWLINE;
closingStatement
    : 'After ' postActivityExpression ', the process finishes.' ;
postActivityExpression
    : activity ' ends' (', ' activity ' ends')*? ;
preActivityExpression
    : activity (', ' activity)*? ;
statement
    : 'After ' postActivityExpression ', immediately start ' preActivityExpression '.' NEWLINE ;
activity
    : '"' WORD (SPACE WORD)*? '"' ;
initialStatement
    : 'Initially start ' preActivityExpression '.' NEWLINE;
statementList
    : statement (statement)*?;
WORD
    : [a-zA-Z0-9]+ ;// a sequence of alphabetic characters
SPACE
    : ' ' ;
NEWLINE
    : '\r'? '\n' ;
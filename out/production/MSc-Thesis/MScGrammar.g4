grammar MScGrammar ;
description
    : leadingStatement initialStatement statementList;

leadingStatement
    : 'The following textual description follows the closed-world assumption, meaning that only the activities specified can be executed in the specified order. Any possible activity and execution that is not specified is considered impossible.' (NEWLINE)*;
initialStatement
    : 'Initially start ' activity '.' (NEWLINE)*;

statementList
    : statement (statement)*?;
statement
    : (afterStatement | closingStatement | asp | osp) (NEWLINE)*;
closingStatement
    : 'After ' postActivityExpression ', the process finishes.' ;

afterStatement
    : 'After ' postActivityExpression ', ' (immediatelyExpression | eventuallyExpression) '.';
asp
    : aspId ': ' activity ' and ' activity (' and ' activity)*? '.';
osp
    : ospId ': ' activity ' or ' activity (' or ' activity)*? '.';

immediatelyExpression
    : 'immediately ' (sequencePreActivityExpression | andPreActivityExpression | orPreActivityExpression | repeatSincePreActivityExpression);
eventuallyExpression
    : 'eventually ' sequencePreActivityExpression;

sequencePreActivityExpression
    : 'start ' activity;
andPreActivityExpression
    : 'start ' (activity | ospId) ' and start ' (activity | ospId) (' and start ' (activity | ospId))*? ;
orPreActivityExpression
    : 'either start ' (activity | aspId) ' or start ' (activity | aspId) (' or start ' (activity | aspId))*? ;
repeatSincePreActivityExpression
    : 'either repeat since ' activity ' or start ' (activity | aspId) (' or start ' (activity | aspId))*?;

postActivityExpression
    : (sequencePostActivityExpression | andPostActivityExpression | orPostActivityExpression);

sequencePostActivityExpression
    : activity ' ends';
andPostActivityExpression
    : (activity | ospId) ' ends and ' ((activity | ospId) ' ends and ')*? (activity | ospId) ' ends';
orPostActivityExpression
    : 'either ' (activity | aspId) ' ends or ' ((activity | aspId) ' ends or ')*? (activity | aspId) ' ends';

activity
    : '"' WORD (SPACE WORD)*? '"' ;
aspId
    : '(' WORD (SPACE WORD)*? ')' ;
ospId
    : '(' WORD (SPACE WORD)*? ')' ;
WORD
    : [a-zA-Z0-9]+ ;
SPACE
    : ' ' | '_' ;
NEWLINE
    : '\r'? '\n' ;
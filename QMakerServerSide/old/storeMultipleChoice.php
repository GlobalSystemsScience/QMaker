<?php

$DATABASE = "qmaker_zxq_qmakerfx";
$USERNAME = "807227_qmaker";
$PASSWORD = "E1nste1n";
$HOST = "localhost";

$question = $_SERVER['q'];
$choices = $_SERVER['c'];
$answers = $_SERVER['a'];
$feedback = $_SERVER['f'];
$type = $_SERVER['t'];
echo $type;

$mysqli = new mysqli($HOST, $USERNAME, $PASSWORD, $DATABASE);
$statement = "INSERT INTO questions (question, choices, answers, feedback, type) VALUES (\"{$question}\", \"{$choices}\", \"{$answers}\", \"{$feedback}\", \"{$type}\")";
$result = $mysqli->query($statement);
if (!$result) {
   printf("%s\n", $mysqli->error);
   exit();
}
echo $mysqli->insert_id;
?>
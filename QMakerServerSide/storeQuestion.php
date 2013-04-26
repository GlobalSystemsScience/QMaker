<?php

$DATABASE = "qmaker_zxq_qmakerfx";
$USERNAME = "807227_qmaker";
$PASSWORD = "E1nste1n";
$HOST = "localhost";

$question = $_GET['q'];
$choices = $_GET['c'];
$answers = $_GET['a'];
$feedback = $_GET['f'];
$type = $_GET['t'];
$tags = json_decode(base64_decode($_GET['tags']), TRUE);

$mysqli = new mysqli($HOST, $USERNAME, $PASSWORD, $DATABASE);
$statement = "INSERT INTO questions (question, choices, answers, feedback, type) VALUES (\"{$question}\", \"{$choices}\", \"{$answers}\", \"{$feedback}\", \"{$type}\")";
$result = $mysqli->query($statement);
if (!$result) {
   printf("%s\n", $mysqli->error);
   exit();
}
$id = $mysqli->insert_id;
echo $id;
foreach ($tags as $tag) {
   $statement = "INSERT INTO tags (qid, tag) VALUES ({$id}, \"{$tag}\")";
   $mysqli->query($statement);
}
?>
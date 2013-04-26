<?php
$DATABASE = "qmaker_zxq_qmakerfx";
$USERNAME = "807227_qmaker";
$PASSWORD = "E1nste1n";
$HOST = "localhost";

$tag = base64_decode($_GET['tag']);

$mysqli = new mysqli($HOST, $USERNAME, $PASSWORD, $DATABASE);
$statement = "SELECT DISTINCT q.question AS question, q.choices, q.answers, q.feedback, q.type FROM questions q, tags t WHERE t.qid=q.id AND t.tag LIKE '%{$tag}%'";
$result = $mysqli->query($statement);
if (!$result) {
   printf("%s\n", $mysqli->error);
   exit();
}
$array = array();
for ($row_no = $result->num_rows - 1; $row_no >= 0; $row_no--) {
    $result->data_seek($row_no);
    $row = $result->fetch_assoc();
    array_push($array, $row);
}
echo json_encode($array);
?>
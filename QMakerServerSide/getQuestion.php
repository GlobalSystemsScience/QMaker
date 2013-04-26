<?php
$USERNAME="807227_qmaker";
$PASSWORD="E1nste1n";
$DATABASE="qmaker_zxq_qmakerfx";
$id = $_GET['qid'];
$query = "SELECT * FROM questions WHERE id={$id}";
$con = mysql_connect("localhost",$USERNAME,$PASSWORD);
if (!$con)
  {
  	echo("bad connection");
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db($DATABASE,$con);

$result=mysql_query($query,$con);
$thisrow=mysql_fetch_array($result);
$thisrow['question'] = base64_decode($thisrow['question']);
$thisrow['choices'] = json_decode(base64_decode($thisrow['choices']));
$thisrow['answers'] = json_decode(base64_decode($thisrow['answers']));
$thisrow['feedback'] = json_decode(base64_decode($thisrow['feedback']));
echo json_encode($thisrow);
mysql_close($con);
?>
<?php
$USERNAME = "807227_qmaker";
$PASSWORD = "E1nste1n";
$DATABASE = "qmaker_zxq_qmaker";

$choiceIDs=json_decode($_REQUEST['choiceIDs']);
$con = mysql_connect("localhost",$USERNAME,$PASSWORD);
if (!$con)
  {
  	echo("bad connection");
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db($DATABASE,$con);
$choices=array();
foreach ( $choiceIDs as $choiceID) {
	$query = "SELECT * FROM choices c WHERE c.id={$choiceID}";
	$result=mysql_query($query,$con);
	$choice=mysql_fetch_array($result);
	if ($choice['comment_id'] != NULL) {
		$query = "SELECT comment FROM comments c WHERE c.id={$choice['comment_id']}";
		$result=mysql_query($query,$con);
		$thisrow=mysql_fetch_array($result);
		$choice['comment_id'] =  $thisrow['comment'];
	}
	array_push($choices, json_encode($choice));
}

echo json_encode($choices);
mysql_close($con);
?>
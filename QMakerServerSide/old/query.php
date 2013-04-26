<?php
$USERNAME="807227_qmaker";
$PASSWORD="E1nste1n";
$DATABASE="qmaker_zxq_qmaker";

$query=$_REQUEST['query'];
$con = mysql_connect("localhost",$USERNAME,$PASSWORD);
if (!$con)
  {
  	echo("bad connection");
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db($DATABASE,$con);

$result=mysql_query($query,$con);
$thisrow=mysql_fetch_array($result);
echo json_encode($thisrow);
mysql_close($con);
?>

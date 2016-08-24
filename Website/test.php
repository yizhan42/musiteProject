<?php
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$target_dir = "/storage/www/musite_dev/public_html/newMuSite";
//$fp = fopen($_FILES['fileToUpload']['tmp_name'], 'rb');
$uploadOk = 1;

if(isset($_POST["submit"])) {
	if(!unlink("Assignment 7.docx"))
		echo "Error";
	//echo $target_file;
	//if(!move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file))
	//	echo "error";

}
// Check file size
if ($_FILES["fileToUpload"]["size"] > 500000) {
    echo "Sorry, your file is too large.";
    $uploadOk = 0;
}

?>


<!DOCTYPE html>
<html>
<body>

<form action="test.php" method="post" enctype="multipart/form-data">
    Select file to upload:
    <input type="file" name="fileToUpload" id="fileToUpload">
    <input type="submit" value="Upload file" name="submit">
</form>

</body>
</html>
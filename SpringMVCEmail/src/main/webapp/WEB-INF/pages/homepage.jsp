<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Email Example</title>
  </head>
  <body onload="onActivate();">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/5.8.2/js/jquery.jqGrid.min.js" integrity="sha512-MMPWQuKgra1rVM2EEgZDWD3ZKmaNALAfKEb+zgkDgET/AS8bCWecd12zqYWoYQ+gpBqoIebb4k/686xHO4YkLQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/5.8.2/css/ui.jqgrid.min.css" integrity="sha512-1tk2HnJ0rlO7C6UpPGU0N8eDX1UB0IekyUlv8UjrApkwedOrlrq8M7SMZpj0Xp6CLz3BFGCBB9iTb9fPv7azsA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Form Starts -->

	<div class="container mt-5 text-center">

		<span id="cnt"><h1>Email Sender</h1></span>
		
		<table id="List"></table>
		<div id="Pager" style="font-size: 11px; text-size:120px;"></div>
					
		<!-- 
		<div class="container mt-30">
			<form>
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter email"> <small
						id="emailHelp" class="form-text text-muted">We'll never
						share your email with anyone else.</small>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Password">
				</div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1">
					<label class="form-check-label" for="exampleCheck1">Check
						me out</label>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div> -->
	</div>
	
<script type="text/javascript">
window.setInterval(function(){
	const st = document.getElementById("cnt");
	st.innerHTML = "Showing xym";
}, 2000);

function onActivate(){
	getTableData();
}

function getTableData(){
	
	var url= '/SpringMVCEmail/mail/getTableData';
	
	$.ajax({
		type: "GET",
		url : url,
		dataType: 'json',
 		async : true,  
		contentType: 'application/json',
		success : function(result) {
				alert("Success");	
				console.log(result.TriggeredOn + result.message);
				
				showTable(result);
				
		}
}); 
	
}

function showTable(data){
	
	var colNameData=['DATE','MESSAGE'];
	

	
	var grid = $("#List"); 		
	var mydata = data;
	
	var colModelData=[
		{name:'TriggeredOn',index:'TriggeredOn',  fixed:true, sortable:false, resize:false},
		{name:'message',index:'message', fixed:true, sortable:false, resize:false,sortable:true}	
	];
	

	
    grid.jqGrid({
        datatype: "local",
        data: mydata,
        pager:'#Pager1',
        colNames:colNameData,
        colModel:colModelData,
        rowNum: 10,
        shrinkToFit:false,
        rowList: [10,100,500,1000,5000,10000],
        //rownumbers: true,
        autoResizing: { compact: true },
        jsonReader: {cell:""},
        //sortorder: 'asc',
        iconSet: "fontAwesome",
        toolbar: [true, "top"],
        autosearch: true,
        stringResult: true,
        //searchOnEnter: true,
        //defaultSearch: "cn"
        viewrecords: true,
		ignoreCase: true,
      }).jqGrid("navGrid", "#Pager", 
    		  {refresh: false,edit:false,add:false,del:false,search:false,pdf:true, excel: true}
	  ); 
   
}

</script>

</body>
</html>
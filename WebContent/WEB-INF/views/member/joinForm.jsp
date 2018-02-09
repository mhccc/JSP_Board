<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>JSP_Board</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../resources/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../resources/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../resources/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../resources/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition register-page">

<div class="register-box">
  <div class="register-logo">
    <a href="../resources/index2.html"><b>JSP_Board</b></a>
  </div>

  <div class="register-box-body">
    <form action="join.do" method="post" onsubmit="return checkForm();">
      <div class="form-group has-feedback">
      	<label class="control-label" for="userid">UserID</label>
        <input type="text" id="userid" name="userid" class="form-control" maxlength="15" required autofocus>
        <span class="form-control-feedback" id="useridFeedback"></span>
        <div>
          	<h6 style="color:red;" id="useridCheckMessage"></h6>
        </div>
      </div>
      <div class="form-group has-feedback">
      	<label class="control-label" for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" maxlength="10" required>
        <span class="form-control-feedback" id="passwordFeedback"></span>
        <div>
          	<h6 style="color:red;" id="passwordCheckMessage"></h6>
        </div>
      </div>
      <div class="form-group has-feedback">
      	<label class="control-label" for="username">Name</label>
        <input type="text" id="username" name="username" class="form-control" maxlength="15" required>
        <span class="form-control-feedback" id="usernameFeedback"></span>
      </div>
      <div class="form-group has-feedback">
      	<label class="control-label" for="email">Email</label>
        <input type="email" id="email" name="email" class="form-control" maxlength="30" required>
        <span class="form-control-feedback" id="emailFeedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox" id="agree"> I agree to the <a href="#">terms</a>
            </label>
          </div>
          <div>
          	<h6 style="color:red;" id="agreeCheckMessage"></h6>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign up</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <div class="social-auth-links text-center">
      <p>- OR -</p>
      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up using Facebook</a>
    </div>

    <a href="login.html" class="text-center">I already have a membership</a>
  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="../resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="../resources/plugins/iCheck/icheck.min.js"></script>

<script>
  $(function() {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
  
  $('#userid').keyup(function() {
	  var len = $('#userid').val().length;
	  
	  if (len == 0) {
		  $('#useridFeedback').attr("class", "form-control-feedback");
		  $('#useridCheckMessage').html('');
	  } else if (len < 5) {
		  $('#useridFeedback').attr("class", "glyphicon glyphicon-remove form-control-feedback");
		  $('#useridCheckMessage').html('5자 이상 입력해주세요.');
	  } else {
		  var userid = $('#userid').val();
		  console.log(userid);
		  $.ajax({
			  type: 'POST',
			  url: 'JoinCheck.do',
			  data: {userid: userid},
			  success: function(result) {
				  if (result == "duplicate") {
					  $('#useridFeedback').attr("class", "glyphicon glyphicon-remove form-control-feedback");
					  $('#useridCheckMessage').html('이미 사용중인 아이디입니다.');
				  } else {
					  $('#useridFeedback').attr("class", "glyphicon glyphicon-ok form-control-feedback");
					  $('#useridCheckMessage').html('');
				  }
			  }
		  })
	  }
  });
  
  $('#password').keyup(function() {
	  var len = $('#password').val().length;
	  
	  if (len == 0) {
		  $('#passwordFeedback').attr("class", "form-control-feedback");
		  $('#passwordCheckMessage').html('');
	  } else if (len < 4) {
		  $('#passwordFeedback').attr("class", "glyphicon glyphicon-remove form-control-feedback");
		  $('#passwordCheckMessage').html('4자 이상 입력해주세요.');
	  } else {
		  $('#passwordFeedback').attr("class", "glyphicon glyphicon-ok form-control-feedback");
		  $('#passwordCheckMessage').html('');
	  }
  });
  
  $('#username').keyup(function() {
	  var len = $('#username').val().length;
	  
	  if (len == 0) {
		  $('#usernameFeedback').attr("class", "form-control-feedback");
	  } else {
		  $('#usernameFeedback').attr("class", "glyphicon glyphicon-ok form-control-feedback");
	  }
  });
  
  $('#email').keyup(function() {
	  var len = $('#email').val().length;
	  
	  if (len == 0) {
		  $('#emailFeedback').attr("class", "form-control-feedback");
	  } else {
		  $('#emailFeedback').attr("class", "glyphicon glyphicon-ok form-control-feedback");
	  }
  });
  
  function checkForm() {
	  if (!$("input:checkbox[id='agree']").is(":checked")) {
		  $('#agreeCheckMessage').html('약관에 동의해주세요.');
		  return false;
	  }
  }
  
</script>

</body>
</html>

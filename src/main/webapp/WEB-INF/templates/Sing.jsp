<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="css/Style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/Object.js">
    </script>
    <script type="text/javascript" src="js/Init.js">
    </script>
    <script type="text/javascript" src="js/Functions.js">
    </script>
    <script type="text/javascript">
        LoadArr();
        function GetValues() {
                var name = document.getElementsByClassName("form-control")[0].value;
                var pas = document.getElementsByClassName("form-control")[1].value;
                CheckIn(name, pas);
                if (localStorage.t === 1) {
                    GoToPage('Score.html');
                    document.location.href = 'Score.html';
                }
            }

        function Go() {
            GoToPage('Score.html');
                document.location.href = 'Score.html';
        }
    </script>

    <title>Вход</title>
</head>

<body class="bggreen">
<div class="container" id ="idbg">
    <br>
    <div class="container">
        <form class="form-signin">
            <div class="row">
                <div class="col"> <br> <br> <br> </div>
            </div>
            <div class="row">
                <div class="col"></div>
                <div class="card">
                    <article class="card-body">
                        <h4 class="card-title text-center mb-4 mt-1">Войдите в свой аккаунт</h4>
                        <hr>
                        <form>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input name="" class="form-control" placeholder="Email or login" type="email" required>
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input class="form-control" placeholder="******" type="password">
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block" onclick="GetValues(); Go()"> Войти  </button>
                            </div> <!-- form-group// -->
                            <p class="text-center"><a href="#" class="btn">Забыли пароль?</a></p>
                            <p class="text-center"><a href="Registered.jsp" class="btn">Регистрация</a></p>
                        </form>
                    </article>
                </div> <!-- card.// -->
                <div class="col"></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="css/Style.css" rel="stylesheet">
    <link href="css/Menu.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
    <script type="text/javascript" src="js/Exit.js">
    </script>
    <script type="text/javascript" src="js/Init.js">
    </script>
    <script type="text/javascript" src="js/Functions.js">
    </script>
    <script type="text/javascript">
        localStorage.curStorage = -1;
        LoadArr();
        function SaveStorage()
        {
            var name = document.getElementsByClassName('name')[0].value;
            var number = document.getElementsByClassName('number')[0].value;
            var type = document.getElementsByClassName('type')[0].value;
            var cash = document.getElementsByClassName('cash')[0].value;
            if (localStorage.curStorage < 0 || localStorage.curStorage > arrUsers[cur].moneyStorage.length - 1)
            {
                AddMoneyStorage(name, number, type, cash);
            }
            else
            {
                ChangeMoneyStorage(name, number, type, cash);
            }
        }
    </script>

    <title>Счет</title>
</head>
<body class="menu">
<header>
    <div id="header_h">
        <script type="text/javascript" src="js/Menu.js">
        </script>
    </div>
</header>
<br>
<div class="container">
    <br>
    <div class="container">
        <div class="row" style="text-align: center">
            <div class="col" style="text-align: center">
                <h3 style="text-align: center"> Счет </h3>
            </div>
        </div>
        <div class="form-table">
            <script type="text/javascript">
                var txt = [];
                Storage(txt);
                document.getElementsByClassName('form-table')[0].innerHTML = txt.pop();
            </script>
        </div>
        <br>
        <form class="form-signin">
            <div class="row">
                <div class="col">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <button type="submit" class="btn btn-primary btn-block" onclick=" SaveStorage(); GoToPage('Score.jsp')"> Сохранить </button>
                </div>
            </div>
            <div class="row">
                <div class="col">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
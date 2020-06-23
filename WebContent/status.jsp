<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-163986832-1"></script>
    <script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());

    gtag('config', 'UA-163986832-1');
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OnToology Drag&amp;Drop</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="/static/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Spartan:wght@300;400&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/node-uuid/1.4.7/uuid.min.js">
    </script>

</head>

<body>
     <header>
       <a href="/app" ><img src="static/images/logoprop.png" alt="" width="250" height="50"></a> 
       <span class="material-icons">
        menu
        </span>
        <nav>
            <span class="material-icons">
                menu_open
                </span>        
            <ul>
                <li> <a href="/app">App</a></li>
                <li> <a href="/stepbystep"> Step by Step</a></li>
                <li> <a href="/about">About</a> </li>
                <li> <a href="/faq">FAQ</a></li>
            </ul>
        </nav>
    </header>

    <main>

        <h2>Your download will be ready in a moment</h2>

        <div id="flexContainer">
            <div>
                <h3></h3>
                <img id="wait" src="static/images/loading.gif">
            </div>
            <textarea name="console" id="console" cols="70" rows="20" disabled></textarea>
        </div>



        <div id="status">
            <div>
                <p>WIDOCO</p>
                <span id="widoco" class="material-icons">
                    radio_button_unchecked
                </span>
                <p id="widoco"></p>
            </div>
            <div>
                <p>OOPS!</p>
                <span id="oops" class="material-icons">
                    radio_button_unchecked
                </span>
                <p id="oops"></p>
            </div>
            <div>
                <p>THEMIS</p>
                <span id="themis" class="material-icons">
                    radio_button_unchecked
                </span>
                <p id="themis"></p>
            </div>
            <div>
                <p>ASTREA</p>
                <span id="astrea" class="material-icons">
                    radio_button_unchecked
                </span>
                <p id="astrea"></p>
            </div>


        </div>

        <button id="download" class="download" disabled>DOWNLOAD</button>

    </main>

    <footer>
        <p>Ontology Engineering Group | Powered by Widoco, Astrea, Themis and OOPS! </p>  
        <img src="static/images/OEG.png" onclick="window.open('https://www.oeg-upm.net/', '_blank');win.focus();" style="cursor: pointer;">
        <img src="static/images/ETSIInf.png" onclick="window.open('https://www.fi.upm.es/', '_blank');win.focus();" style="cursor: pointer;">
        <img src="static/images/UPM.png" onclick="window.open('https://www.upm.es/', '_blank');win.focus();" style="cursor: pointer;">
    </footer>

    <script src="/static/status.js"></script>
    <script src="/static/menu.js"></script>

      

</body>

</html>
function logout()
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "http://localhost:8080/QACinemas/logout", false ); // false for synchronous request
    xmlHttp.send( null );
    window.location.replace("http://localhost:8080/QACinemas/web/admin/admin.html");
}
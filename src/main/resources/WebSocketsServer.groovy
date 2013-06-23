import groovy.json.JsonBuilder

def server = vertx.createHttpServer()

server.websocketHandler { ws ->
    ws.dataHandler { data ->
        def input = [:]
        input.input = "$data"
        input.timeStamp = new Date().format("yyyy-MM-dd HH:mm:ss SSS")

        ws.writeTextFrame(new JsonBuilder(input).toString())
    }
}.requestHandler { req ->
    if (req.uri == "/") req.response.sendFile "ws.html"
}.listen(8080)

<?php
    require("config.php");
    $datos = array();
    $accion = "";
    if(isset($_POST["accion"])){
        $accion = $_POST["accion"];
    }

    if($accion=="registrar"){
        $nombre = $_POST["nombre"];
        $telefono = $_POST["telefono"];
        if(agregarContacto($nombre,$telefono)==1){
           $datos["estado"] = 1;
           $datos["resultado"] = "Datos Almacenados con exito"; 
        }else{
           $datos["estado"] = 0;
           $datos["resultado"] = "Error, no se pudo almacenar los datos"; 
        }        
    }else if($accion=="listar_contactos"){
        $filtro = "";
        if(isset($_POST["filtro"])){
           $filtro = $_POST["filtro"]; 
        }
        
        $datos["estado"] = 1;
        $datos["resultado"] = listarContacto($filtro);
    }else if($accion=="modificar"){
        
    }else if($accion=="eliminar"){
        
    }

    echo json_encode($datos);
?>

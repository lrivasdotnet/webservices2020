<?php
    $datos = array();
    $accion = "";
    $valor1 = "";
    $valor2 = "";

    if(isset($_GET["accion"])){
        $accion = $_GET["accion"];
    }

    if(isset($_POST["accion"])){
        $accion = $_POST["accion"];
    }

    if(isset($_POST["valor1"])){
       $valor1 = $_POST["valor1"]; 
    }

    if(isset($_GET["valor1"])){
       $valor1 = $_GET["valor1"]; 
    }

    if(isset($_POST["valor2"])){
       $valor2 = $_POST["valor2"];
    }

    if(isset($_GET["valor2"])){
       $valor2 = $_GET["valor2"];
    }

    if($accion=="sumar"){
        $datos["accion"] = $accion;
        $datos["resultado"] = $valor1 + $valor2;
    }else if($accion=="restar"){
        $datos["accion"] = $accion;
        $datos["resultado"] = $valor1 - $valor2;
    }else if($accion=="multiplicar"){
      $datos["accion"] = $accion;
        $datos["resultado"] = $valor1 * $valor2;
    }else if($accion=="dividir"){
        $datos["accion"] = $accion;
        $datos["resultado"] = $valor1 / $valor2;
    }else if($accion=="dec2bin"){
        $datos["accion"] = $accion;
        $datos["resultado"] = decbin($valor1);
    }else if($accion=="bin2dec"){
        $datos["accion"] = $accion;
        $datos["resultado"] = bindec($valor1);
    }
    echo json_encode($datos);
?>


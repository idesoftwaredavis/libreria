
$(document).ready(function(){
	// selecciono el form
	$("#frm").submit(function(event){
		event.preventDefault();
		autenticarUsuario();
	});
	
	$("#form-register").submit(function(event){
		//previene la accion por defecto del submit
		
		event.preventDefault();
		registrarUsuario();
	});
	
	
});

function registrarUsuario(){
	let username = $("#input-username").val();
	let contrasena = $("#input-contrasena").val();
	let contrasenaConfirm = $("#input-contrasena-repeat").val();
	let nombre = $("#input-nombre").val();
	let apellidos = $("#input-apellidos").val();
	let email = $("#input-email").val();
	let saldo = $("#input-saldo").val();
	let premium = $("#input-premium").prop("checked");
	
	if ( contrasena == contrasenaConfirm){
		
		$.ajax({
			type:"GET",
			dataType:"html",
			url:"./ServletUsuarioRegister",
			data: $.param({
				username:username,
				contrasena: contrasena,
				nombre: nombre,
				apellidos: apellidos,
				email: email,
				saldo: saldo,
				premium: premium
			}),
			success: function(r){
				let parse = JSON.parse(r);
				
				if ( parse != false ){
					$("#register-error").addClass("d-none");
					let username = parse['username'];
					window.location.href="home.html?username="+username;
					
				}else{
					$("#register-error").removeClass("d-none");
					$("#register-error").html("Error en el registro del usuario");
				}
			}
		});
		
		
		
	}else{
		$("#register-error").removeClass("d-none");
		$("#register-error").html("Las contraseñas no coinciden");
	}
}


function autenticarUsuario(){
	let username = $("#usuario").val();
	let contrasena = $("#contrasena").val();
	
	
	$.ajax({
		type:"GET",
		dataType:"html",
		url:"./ServletUsuarioLogin",
		data: $.param({
			username: username,
			contrasena: contrasena
		}),
		success: function(result){
			
			// parsear JSON
			let parseResult = JSON.parse(result);
			
			if(parseResult != false){
				$("#login-error").addClass("d-none");
			
				let username = parseResult["username"];
				document.location.href = "home.html?username="+username;
			}else{
				$("#login-error").removeClass("d-none");
			}
		}
	})
}
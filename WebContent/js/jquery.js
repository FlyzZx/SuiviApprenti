jQuery(document).ready(function(){
	$(document).on("focus", "#nomEntreprise", function() { //Utilisation de on pour garder les elements après une requete AJAX
		$(this).autocomplete({ //AJAX
			source:  "logged/ajax/enterprises.jsp?choix=autocomplete",
			minLength: 1,
			select: function(event, ui) {
				
				//ui.item.label = ui.item.label.replace(/\n|\r|(\n\r)/g,''); //Efface les retour chariot etc ...
			    //alert(ui.item.label);
				$.ajax({
					type: "GET",
					url: "logged/ajax/enterprises.jsp",
					data: "choix=entchoose&term=" + ui.item.label,
					dataType: "json",
					success: function(retour){
						$("#brancheEntreprise").attr("value", retour.branche);
						$("#nbSalariesEntreprise").attr("value", retour.nbSalaries);
						$("#rueEntreprise").attr("value", retour.rue);
						$("#villeEntreprise").attr("value", retour.ville);
						$("#codePostalEntreprise").attr("value", retour.codePostal);
						$("#telephoneEntreprise").attr("value", retour.telephone);
						$("#mobileEntreprise").attr("value", retour.mobile);
						$("#emailEntreprise").attr("value", retour.email);
						$("#siteEntreprise").attr("value", retour.site);
						$("#identreprise").attr("value", retour.identreprise);
					}				
				});
			}
		}); 
	});

	$("#dateDebut").datepicker({
		dateFormat: "yy-mm-dd",
		autoSize: true,

	});
	
	$(document).on("click", "#btn_connect", function(){
		$(".spinner").show();
	});
});
import requests
key = "a52e18293d5e9335bbf5a4eb6d29c8ec"
numb = input("Numero a rastrear: ")
url = "http://apilayer.net/api/validate?access_key="+key+"&number="+numb+"&country_code=&format=1"
r = requests.get(url)
r_dict = r.json()
print("Lada: "+r_dict['country_prefix']+"\nCodigo de pais: "+r_dict['country_code']+"\nNumero: "+r_dict['number']+"\nLocal: "+r_dict['local_format']+"\nInternacional: "+r_dict['international_format']+"\nPais: "+r_dict['country_name']+"\nUbicacion: "+r_dict['location']+"\nCompañia: "+r_dict['carrier']+"\nTipo de linea: "+r_dict['line_type'])

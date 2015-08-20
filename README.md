# Nominatim-Parser
Parser for OpenStreetMap Nominatim API
Written for Android in Java
Easy and efficient replacement for Google Places API

#Example
 Request.getPlaces(new Action() {
           
            @Override
            public void action(Place e) {
                Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        },
        
        new HashMap<String, String>(){
        
            {
                put("q","test");
                put("countrycodes", "fr");
            }
        },
        new HashMap<String, String>(){
            {
                put("street", "3 Rue du Blé Noir");
                put("countrycodes", "fr");
            }
        });

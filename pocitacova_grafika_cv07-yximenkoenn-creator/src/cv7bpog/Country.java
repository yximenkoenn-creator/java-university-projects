
package cv7bpog;

import javafx.beans.property.SimpleStringProperty;
public class Country {
    private final SimpleStringProperty name;
    private final SimpleStringProperty continent;

    Country(String name,String continent) {
        this.name = new SimpleStringProperty(name);
        this.continent = new SimpleStringProperty(continent);
    }

    public String getName() {
        return name.get();
    }

    public String getContinent() {
        return continent.get();
    }

  public void setName(String name){
     this.name.set(name);
  }
    
    public void setContinent(String continent){
        this.continent.set(continent);
    }
}

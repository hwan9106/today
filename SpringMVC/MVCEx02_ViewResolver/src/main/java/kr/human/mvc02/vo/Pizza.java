package kr.human.mvc02.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "pizza")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name","flavor", "toppings"})
@Data
@NoArgsConstructor
public class Pizza {
	@XmlElement
    private String name;
	@XmlElement
    private String flavor;
	@XmlElement
    private List<String> toppings = new ArrayList<String>();
     
    public Pizza(String name){
        this.name = name;
        this.flavor = "spicy";
        this.toppings.add("Cheese");
        this.toppings.add("bakon");
    }
}
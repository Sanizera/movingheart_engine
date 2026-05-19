package com.game.objects;

import java.util.ArrayList;
import java.util.List;
public class GameObject {
    
    public Transform transform;
    private List<Component> components;
    public GameObject(){
        transform = new Transform();

        components = new ArrayList<>();

    }

    public void addComponent(Component c){
        c.gameObject = this;
        components.add(c);
    }
    public <T extends Component> T getComponent(Class<T> type){
        for(Component c: components){
            if (type.isInstance(c)){
                
                return type.cast(c);
            }
        }
        return null;
    }

    public void update(float deltaTime){
        for(Component c: components){
            c.update(deltaTime);
        }
    }

    public void render(){
        for(Component c : components){
            c.render();
        }
    }
}

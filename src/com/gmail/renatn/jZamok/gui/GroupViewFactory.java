package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.model.PasswordGroup;

/**
 *
 * @author renat
 */
public class GroupViewFactory {
       
    public static GroupView createView(PasswordGroup group, int viewAs) {
        GroupView view;
        
        switch (viewAs) {
            case GroupView.LIST_VIEW :
                view = new GroupListView(group);
                break;
            case GroupView.TABLE_VIEW :
                view = new GroupTableView(group);
                break;
            default :
                view = null;                            
        }
        
        return (GroupView) view;
    }

}

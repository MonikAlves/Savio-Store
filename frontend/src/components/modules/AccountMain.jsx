import React, { useEffect } from "react";
import { UserRoundPlus } from 'lucide-react';
import { UserContext, UserProvider, useUser } from "../../contexts/UserProvider";

export function AccountMain() {

    const {user} = useUser();
    
    console.log(user)

    return (
        <div className="flex flex-row w-full justify-end gap-7 min-h-[80vh] p-5 pt-8">
            <div  className="flex bg-amazon-gunmetal p-3 w-2/3 border-[2px]">
                
            </div>
            <ul className="flex bg-amazon-gunmetal flex-col gap-5 w-1/3 items-center p-5 border-[2px] rounded aspect-square ">
                <li className="flex max-h-[250px] items-center justify-center bg-amazon-apricot h-1/2 w-1/2 border-[2px] rounded">
                 <UserRoundPlus className="flex h-1/2 w-1/2"/>
                </li>
                
                <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[50px] w-full border-[2px] rounded">
                <h2>{user && user.name}</h2>
                </li>

                <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[50px] w-full border-[2px] rounded">
                <h2>{user && user.email}</h2>
                </li>

                <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[50px] w-full border-[2px] rounded">
                <h2>{user && user.phone}</h2>
                </li>

                <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[50px] w-full border-[2px] rounded">
                <h2>{}</h2>
                </li>

            </ul>
        </div>
    );
}
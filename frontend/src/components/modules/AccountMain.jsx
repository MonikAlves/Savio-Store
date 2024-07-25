import React, { useEffect } from "react";
import { UserRoundPlus } from 'lucide-react';
import { UserContext, UserProvider, useUser } from "../../contexts/UserProvider";

export function AccountMain() {

    const {user} = useUser();

    return (
        <div className="flex flex-row w-full max-h-[800px] justify-center gap-7 p-5 pt-6">
            <div className="flex bg-amazon-gunmetal justify-center flex-col gap-3 w-2/3 items-center p-5 border-[2px] rounded ">

            </div>
            <ul className="flex bg-amazon-gunmetal justify-center flex-col gap-3 w-1/3 items-center p-5 border-[2px] rounded aspect-square ">

                <li className="flex max-h-[200px] items-center justify-center bg-amazon-apricot h-1/4 w-1/4 border-[2px] rounded">
                 <UserRoundPlus className="flex h-1/2 w-1/2"/>
                </li>
                
                <div className="flex w-full flex-col">
                    <p className="flex p-1 text-white">User :</p>
                    <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                        <h2>{user && user.name}</h2>
                    </li>
                </div>
                
                <div className="flex w-full flex-col">
                    <p className="flex p-1 text-white">Email :</p>
                    <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                        <h2>{user && user.email}</h2>
                    </li>
                </div>
                

                <div className="flex w-full flex-col">
                    <p className="flex p-1 text-white">Phone :</p>
                    <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                        <h2>{user && user.phone}</h2>
                    </li>
                </div>
                

                <div className="flex w-full flex-col">
                    <p className="flex p-1 text-white">LegalData :</p>
                    <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                        <h2>{user && user.legalData}</h2>
                    </li>
                </div>
                

            </ul>
        </div>
    );
}
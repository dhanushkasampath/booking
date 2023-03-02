import React from 'react';
import axios from 'axios';
import {useState } from "react";


 
function UserRegistration()
{
   
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [mobile, setMobile] = useState("");
    const[department, setDepartment] =useState("");
 
    async function save(event)
    {
      console.log(name);
      console.log(address);
      console.log(mobile);
      console.log(department);
        event.preventDefault();
    try
        {
         await axios.post("http://localhost:8080/api/v1/employee/save-employee",
        {
        
        empName: name,
        empAddress : address,
        empMNumber : mobile,
        departmentId : department
        
        });
          alert("Employee Registation Successfully");
          setName("");
          setAddress("");
          setMobile("");
          setDepartment("");         
        }
    catch(err)
        {
          alert("User Registation Failed");
        }
   }
    return (
      
        <div className="text-center m-5-auto" >

          
        <h2>Join us</h2>
        <h5>Create your personal account</h5>
        <form action = "/home">
        <div class="form-group">
           <p><label>Name</label><br/>
            <input type="text" class="form-control" placeholder="Enter Name" required
             value={name}
            onChange={(event) =>
              {
                setName(event.target.value);      
              }}
            /></p>
        </div>

        

        
 
        <div class="form-group">
            <p><label>Address</label><br/>
            <input type="text" class="form-control" placeholder="Enter Address"
             value={address}
             onChange={(event) =>
               {
                setAddress(event.target.value);      
               }}
            /></p>
        </div>
 
        <div class="form-group">
            <p><label>Mobile</label><br/>
            <input type="text" class="form-control" placeholder="Enter Mobile"
            value={mobile}
            onChange={(event) =>
              {
                setMobile(event.target.value);      
              }}
           /></p>
        </div>

        <div class="form-group">
            <p><label>departmentId</label><br/>
            <input type="text" class="form-control" placeholder="Enter Did"
             value={department}
            onChange={(event) =>
              {
                setDepartment(event.target.value);      
              }}
            /></p>
        </div>
        
        <p>
                    <input type="checkbox" name="checkbox" id="checkbox" required /> <span>I agree all statements in <a href="https://google.com" target="_blank" rel="noopener noreferrer">terms of service</a></span>.
                </p>


        <button class="btn btn-primary mt-4"  onClick={save}>Register</button>
        
        </form>
 
      </div>

      
    );

    
  }
  


  export default UserRegistration;
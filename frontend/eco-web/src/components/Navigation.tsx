
import React from 'react';
import { NavLink } from 'react-router-dom';
import { Home, Search, Heart, User, ShoppingBag } from 'lucide-react';

export const Navigation = () => {
  return (
    <nav className="fixed bottom-0 left-0 right-0 bg-white border-t border-gray-200 px-4 py-2 z-50 shadow-lg">
      <div className="flex justify-around items-center max-w-sm mx-auto">
        <NavLink
          to="/app"
          className={({ isActive }) =>
            `flex flex-col items-center p-3 transition-all duration-200 rounded-xl ${
              isActive 
                ? 'text-green-500 bg-green-50 scale-105' 
                : 'text-gray-500 hover:text-green-500 hover:bg-green-50'
            }`
          }
        >
          <Home size={24} />
          <span className="text-xs mt-1 font-medium">Home</span>
        </NavLink>
        <NavLink
          to="/marketplace"
          className={({ isActive }) =>
            `flex flex-col items-center p-3 transition-all duration-200 rounded-xl ${
              isActive 
                ? 'text-green-500 bg-green-50 scale-105' 
                : 'text-gray-500 hover:text-green-500 hover:bg-green-50'
            }`
          }
        >
          <ShoppingBag size={24} />
          <span className="text-xs mt-1 font-medium">Market</span>
        </NavLink>
        <NavLink
          to="/app/search"
          className={({ isActive }) =>
            `flex flex-col items-center p-3 transition-all duration-200 rounded-xl ${
              isActive 
                ? 'text-green-500 bg-green-50 scale-105' 
                : 'text-gray-500 hover:text-green-500 hover:bg-green-50'
            }`
          }
        >
          <Search size={24} />
          <span className="text-xs mt-1 font-medium">Search</span>
        </NavLink>
        <NavLink
          to="/app/favorites"
          className={({ isActive }) =>
            `flex flex-col items-center p-3 transition-all duration-200 rounded-xl ${
              isActive 
                ? 'text-green-500 bg-green-50 scale-105' 
                : 'text-gray-500 hover:text-green-500 hover:bg-green-50'
            }`
          }
        >
          <Heart size={24} />
          <span className="text-xs mt-1 font-medium">Saved</span>
        </NavLink>
        <NavLink
          to="/app/profile"
          className={({ isActive }) =>
            `flex flex-col items-center p-3 transition-all duration-200 rounded-xl ${
              isActive 
                ? 'text-green-500 bg-green-50 scale-105' 
                : 'text-gray-500 hover:text-green-500 hover:bg-green-50'
            }`
          }
        >
          <User size={24} />
          <span className="text-xs mt-1 font-medium">Profile</span>
        </NavLink>
      </div>
    </nav>
  );
};

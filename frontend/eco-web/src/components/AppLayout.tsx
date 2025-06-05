
import React from 'react';
import { Outlet } from 'react-router-dom';
import { Navigation } from './Navigation';

export const AppLayout = () => {
  return (
    <div className="min-h-screen bg-gray-50">
      <main className="pb-20">
        <Outlet />
      </main>
      <Navigation />
    </div>
  );
};

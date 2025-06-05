
import React from 'react';
import { Separator } from '@/components/ui/separator';
import { Link } from 'react-router-dom';

export const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white py-16">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid md:grid-cols-4 gap-8">
          {/* Brand */}
          <div className="col-span-1">
            <div className="text-2xl font-bold text-green-400 mb-4">
              🌱 EcoMeal
            </div>
            <p className="text-gray-400 mb-6">
              Fighting food waste, one meal at a time. Join our community in building a more sustainable future.
            </p>
            <div className="flex space-x-4">
              <div className="w-10 h-10 bg-gray-800 rounded-full flex items-center justify-center hover:bg-green-600 transition-colors cursor-pointer">
                <span className="text-sm">f</span>
              </div>
              <div className="w-10 h-10 bg-gray-800 rounded-full flex items-center justify-center hover:bg-green-600 transition-colors cursor-pointer">
                <span className="text-sm">t</span>
              </div>
              <div className="w-10 h-10 bg-gray-800 rounded-full flex items-center justify-center hover:bg-green-600 transition-colors cursor-pointer">
                <span className="text-sm">in</span>
              </div>
            </div>
          </div>

          {/* Quick Links */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Quick Links</h3>
            <div className="space-y-2">
              <a href="#features" className="block text-gray-400 hover:text-white transition-colors">Features</a>
              <a href="#how-it-works" className="block text-gray-400 hover:text-white transition-colors">How It Works</a>
              <a href="#impact" className="block text-gray-400 hover:text-white transition-colors">Our Impact</a>
              <a href="#testimonials" className="block text-gray-400 hover:text-white transition-colors">Testimonials</a>
            </div>
          </div>

          {/* Support */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Support</h3>
            <div className="space-y-2">
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Help Center</a>
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Contact Us</a>
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Partner With Us</a>
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Business Solutions</a>
            </div>
          </div>

          {/* Legal */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Legal</h3>
            <div className="space-y-2">
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Privacy Policy</a>
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Terms of Service</a>
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Cookie Policy</a>
              <a href="#" className="block text-gray-400 hover:text-white transition-colors">Food Safety</a>
            </div>
          </div>
        </div>

        <Separator className="my-8 bg-gray-800" />

        <div className="flex flex-col md:flex-row justify-between items-center">
          <div className="text-gray-400 text-sm mb-4 md:mb-0">
            © 2024 EcoMeal. All rights reserved. Made with ❤️ in Chisinau.
          </div>
          <div className="text-gray-400 text-sm">
            Available in Romanian, Russian & English
          </div>
        </div>
      </div>
    </footer>
  );
};

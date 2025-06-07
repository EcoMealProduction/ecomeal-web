
import React, { useState, useEffect } from 'react';
import { Button } from '@/components/ui/button';
import { Menu, X } from 'lucide-react';
import { Link } from 'react-router-dom';
import { LanguageSelector } from '@/components/LanguageSelector';

export const Navbar = () => {
  const [isScrolled, setIsScrolled] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 10);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <nav className={`fixed top-0 left-0 right-0 z-50 transition-all duration-300 ${
      isScrolled ? 'bg-white/95 backdrop-blur-md shadow-lg' : 'bg-transparent'
    }`}>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <div className="flex items-center">
            <Link to="/" className="flex items-center gap-2">
              <img src="/ecomeal.PNG" alt="ecomeal-logo" className="w-12 h-12 object-contain" />
              <h1 className="text-2xl font-bold text-green-500">ecomeal</h1>
            </Link>
          </div>

          {/* Desktop Navigation */}
          <div className="hidden md:flex items-center space-x-8">
            <Link to="/marketplace" className="text-gray-700 hover:text-green-500 transition-colors font-medium">Marketplace</Link>
            <a href="#features" className="text-gray-700 hover:text-green-500 transition-colors font-medium">Features</a>
            <a href="#how-it-works" className="text-gray-700 hover:text-green-500 transition-colors font-medium">How it works</a>
            <a href="#impact" className="text-gray-700 hover:text-green-500 transition-colors font-medium">Impact</a>
            <a href="#testimonials" className="text-gray-700 hover:text-green-500 transition-colors font-medium">Testimonials</a>
          </div>

          {/* CTA Buttons and Language Selector */}
          <div className="hidden md:flex items-center space-x-4">
            <LanguageSelector />
            {/* <Link to="/onboarding">
              <Button variant="outline" className="border-green-500 text-green-500 hover:bg-green-50">
                Sign In
              </Button>
            </Link>
            <Link to="/onboarding">
              <Button className="bg-green-500 hover:bg-green-600 text-white">
                Get Started
              </Button>
            </Link> */}
          </div>

          {/* Mobile menu button */}
          <div className="md:hidden flex items-center space-x-2">
            <LanguageSelector />
            <Button
              variant="ghost"
              size="sm"
              onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
            >
              {isMobileMenuOpen ? <X size={24} /> : <Menu size={24} />}
            </Button>
          </div>
        </div>

        {/* Mobile Navigation */}
        {isMobileMenuOpen && (
          <div className="md:hidden bg-white border-t shadow-lg">
            <div className="px-2 pt-2 pb-3 space-y-1">
              <Link to="/marketplace" className="block px-3 py-2 text-gray-700 hover:text-green-500 font-medium">Marketplace</Link>
              <a href="#features" className="block px-3 py-2 text-gray-700 hover:text-green-500 font-medium">Features</a>
              <a href="#how-it-works" className="block px-3 py-2 text-gray-700 hover:text-green-500 font-medium">How it works</a>
              <a href="#impact" className="block px-3 py-2 text-gray-700 hover:text-green-500 font-medium">Impact</a>
              <a href="#testimonials" className="block px-3 py-2 text-gray-700 hover:text-green-500 font-medium">Stories</a>
              {/* <div className="pt-4 space-y-2">
                <Link to="/onboarding" className="block">
                  <Button variant="outline" className="w-full border-green-500 text-green-500">
                    Sign In
                  </Button>
                </Link>
                <Link to="/onboarding" className="block">
                  <Button className="w-full bg-green-500 hover:bg-green-600 text-white">
                    Get Started
                  </Button>
                </Link>
              </div> */}
            </div>
          </div>
        )}
      </div>
    </nav>
  );
};
